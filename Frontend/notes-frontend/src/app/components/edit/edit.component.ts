import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css'],
})
export class EditComponent {
  noteId: number;
  formData = {
    content: '',
  };

  contentError = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient
  ) {
    const idParam = this.route.snapshot.paramMap.get('id');
    this.noteId = idParam !== null ? +idParam : 0;
  }

  ngOnInit() {
    this.loadNoteData();
  }

  loadNoteData() {
    this.contentError = false;

    this.http
      .get<any>(`http://localhost:8088/api/notes/${this.noteId}`)
      .subscribe({
        next: (data) => {
          this.formData.content = data.content;
        },
        error: (error) => {
          console.error(`Failed to load note with ID ${this.noteId}:`, error);
        },
      });
  }

  onSubmit() {
    this.contentError = false;

    if (!this.formData.content || this.formData.content.length > 500) {
      this.contentError = true;
      return;
    }

    const noteData = {
      content: this.formData.content,
    };

    this.http
      .put<any>(`http://localhost:8088/api/notes/edit/${this.noteId}`, noteData)
      .subscribe({
        next: () => {
          this.router.navigate(['/home']);
        },
        error: (error) => {
          console.error(`Failed to edit note with ID ${this.noteId}:`, error);
        },
      });
  }
}
