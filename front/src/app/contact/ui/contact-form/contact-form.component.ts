import { Component, inject } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { MessageService } from "primeng/api";
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { InputTextModule } from "primeng/inputtext";
import { ToastModule } from 'primeng/toast';


@Component({
  selector: "app-contact-form",
  standalone: true,
  imports: [FormsModule, ButtonModule, InputTextModule, CardModule, ToastModule],
  templateUrl: "./contact-form.component.html",
  styleUrl: "./contact-form.component.css",
})
export class ContactFormComponent {

  private readonly messageService = inject(MessageService);
  email: string = "";
  message: string = "";

  onSend() {
    if (!this.email || !this.message) {
      return;
    }

    if(this.email.length < 10 || this.message.length >300){
      return;
    }

    console.log('Email:', this.email);
    console.log('Message:', this.message);

    this.messageService.add({ severity: 'success', summary: 'Succès', detail: 'Demande de contact envoyée avec succès' });

    this.email = '';
    this.message = '';
  }
}
