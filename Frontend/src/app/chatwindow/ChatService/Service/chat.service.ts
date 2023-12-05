import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { WebSocketSubject } from 'rxjs/webSocket';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private socket$!: WebSocketSubject<any>;

  connect(url: string): void {
    this.socket$ = new WebSocketSubject(url);
  }

  sendMessage(message: any): void {
    this.socket$.next(message);
  }

  onMessage(): Observable<any> {
    return this.socket$.asObservable().pipe(map(data => JSON.parse(data)));
  }

  closeConnection(): void {
    this.socket$.complete();
  }
}
