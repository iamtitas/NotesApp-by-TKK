import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Note } from '../../model/note.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  notes: Note[] = [];

  constructor(private router: Router, private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchNotes();
  }

  doNothing(event: Event) {
    event.stopPropagation();
  }

  username = sessionStorage.getItem('username');

  logout() {
    sessionStorage.removeItem('username');

    this.router.navigate(['/login']);
  }

  fetchNotes() {
    const username = sessionStorage.getItem('username');

    if (!username) {
      console.error('Username is not available');
      return;
    }

    const apiUrl = `http://localhost:8088/api/notes/recent/${username}`;

    this.http.get<Note[]>(apiUrl).subscribe(
      (data) => {
        this.notes = data;
      },
      (error) => {
        console.error('Failed to fetch notes:', error);
      }
    );
  }

  goToAddNote() {
    this.router.navigate(['/add']);
  }

  editNote(noteId: number) {
    this.router.navigate(['/edit', noteId]);
  }

  deleteNoteConfirm(noteId: number) {
    const confirmDelete = confirm('Are you sure you want to delete this note?');

    if (confirmDelete) {
      this.http
        .delete<void>(`http://localhost:8088/api/notes/delete/${noteId}`)
        .subscribe(
          () => {
            this.fetchNotes();
          },
          (error) => {
            console.error(`Failed to delete note with ID ${noteId}:`, error);
          }
        );
    }
  }
}
