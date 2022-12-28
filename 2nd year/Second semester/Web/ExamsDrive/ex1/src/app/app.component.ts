import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'exemplu1';
  public username = "";

  loginForm = new FormGroup({
    userName: new FormControl('', Validators.required),
  })

  public onSubmit(): void{
  }

  public connect(username: string): void{
    sessionStorage.setItem('username', username); 
  }
}
