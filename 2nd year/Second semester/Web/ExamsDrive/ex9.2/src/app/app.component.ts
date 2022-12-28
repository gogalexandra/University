import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'exemplu9';
  public userName = "";

  loginForm = new FormGroup({
    userName: new FormControl('', Validators.required),
  })

  public onSubmit(): void{
  }

  public connect(username: string): void{
    sessionStorage.setItem('username', username); 
  }
}
