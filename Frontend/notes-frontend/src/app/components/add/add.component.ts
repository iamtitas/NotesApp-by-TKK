import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css'],
})
export class AddComponent {
  formData = {
    content: '',
  };

  contentError = false;

  constructor(private router: Router, private http: HttpClient) {}

  onSubmit() {
    this.contentError = false;

    if (!this.formData.content || this.formData.content.length > 500) {
      this.contentError = true;
      return;
    }

    const username = sessionStorage.getItem('username');

    const noteData = {
      content: this.formData.content,
      username: username,
    };

    this.http
      .post<any>('http://localhost:8088/api/notes/create', noteData)
      .subscribe({
        next: () => {
          this.router.navigate(['/home']);
        },
        error: (error) => {
          console.error('Failed to create a note:', error);
        },
      });
  }
}
