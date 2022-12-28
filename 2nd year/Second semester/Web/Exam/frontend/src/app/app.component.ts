import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'exam';
  public username = "";

  loginForm = new FormGroup({
    username: new FormControl('', Validators.required),
  })

  public onSubmit(): void{
  }

  public connect(username: string): void{
    sessionStorage.setItem('username', username); 
  }
}
