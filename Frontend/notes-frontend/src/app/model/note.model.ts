export class Note {
  id: number;
  content: string;
  user: string;
  timestamp: number;

  constructor(id: number, content: string, user: string, timestamp: number) {
    this.id = id;
    this.content = content;
    this.user = user;
    this.timestamp = timestamp;
  }
}
