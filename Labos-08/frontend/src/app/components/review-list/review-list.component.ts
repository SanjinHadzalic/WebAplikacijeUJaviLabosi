import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormsModule, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ReviewService } from '../../services/review.service';
import { Review } from '../../interfaces/review';
import { Vozilo } from '../../interfaces/vozilo';
import { Router } from '@angular/router';
import { VoziloService } from '../../services/vozilo.service';
import { NavbarComponent } from '../navbar/navbar.component';
@Component({
  selector: 'app-review-list',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, NavbarComponent],
  templateUrl: './review-list.component.html',
  styleUrl: './review-list.component.css',
  providers: [ReviewService, VoziloService]
})
export class ReviewListComponent implements OnInit{
  reviews: Review[] = [];
  newReview!: Review;
  vozila!: Vozilo[];
  selectedVoziloId!: number;

  constructor(private reviewService: ReviewService, private vozilaService: VoziloService, private router: Router) {
    this.reviewService.getReviewList().subscribe(res => {
      this.reviews = res;
    })
  }

  private getReviews() {
    this.reviewService.getReviewList().subscribe(review=>{
      this.reviews = review;
    })
  }

  private getVozila() {
    this.vozilaService.getVoziloList().subscribe(voz=>{
      this.vozila = voz;
    })
  }

  reviewForm = new FormGroup({
    title: new FormControl("", [
      Validators.required
    ]),
    text: new FormControl("", [
      Validators.required
    ]),
    grade: new FormControl("", [
      Validators.required
    ]),
    vozilo_id: new FormControl("", [
    ])
  })

  ngOnInit(): void {
    this.getVozila()
    this.getReviews();
    this.newReview = {
      id: 1,
      title: '',
      text: '',
      grade: 1,
      vozilo: {
        id: 3, 
        registration: '',
        vin: '',
        maxNumberOfPassenger: 0,
        shifter: 'normal',
        airConditioning: '',
        numberOfDoors: 0, 
        fuelType: '',
        lastServiceDate: new Date(2023,2,2),
        nextServiceDate: new Date(2024,2,2),
        mileage: 24534
      }
    }
  }

  onReviewClick(id:number){
    this.router.navigate(['review', id]);
  }

  deleteReview(id:number) {
    this.reviewService.deleteReview(id).subscribe(data => {
      this.getReviews();
      this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
        this.router.navigate(['/review']);
      })
      console.log(data)
    })
  }

  onSubmit() {
    this.saveReview();
  }

  saveReview() {
    const formValue = this.reviewForm.value;
  
    this.newReview.id = Math.max(...this.reviews.map(r=>r.id)) + 1;
    this.newReview.title = formValue.title!;
    this.newReview.text = formValue.text!;
    this.newReview.grade = parseInt(formValue.grade!);
    this.newReview.vozilo = this.vozila[this.selectedVoziloId - 1]
  
    this.reviewService.createReview(this.newReview).subscribe({
      next: (data) => {
        console.log('Unutar servisa:', data);
        location.reload(); 
      },
      error: (e) => {
        console.log('error:', e);
      }
    });
  }

  reviewDetails(id: number) {
    this.router.navigate(['review/id', id]);
  }

  returnToHome() {
    this.router.navigate([''])
  }
}
