import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormsModule, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ReviewService } from '../../services/review.service';
import { Review } from '../../interfaces/review';
import { Vozilo } from '../../interfaces/vozilo';
import { Router } from '@angular/router';
import { VoziloService } from '../../services/vozilo.service';
@Component({
  selector: 'app-review-list',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './review-list.component.html',
  styleUrl: './review-list.component.css',
  providers: [ReviewService, VoziloService]
})
export class ReviewListComponent implements OnInit{
  reviews: Review[] = [];
  newReview!: Review;
  vozila!: Vozilo[];

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
    ])
  })

  ngOnInit(): void {
    this.getReviews();
    this.getVozila
    this.newReview = {
      id: 6,
      title: '',
      text: '',
      grade: 1

    }
    // throw new Error('Method not implemented.');
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
    console.log(this.newReview)
    this.saveReview();
  }

  saveReview() {
    const formValue = this.reviewForm.value;
  
    this.newReview.title = formValue.title!;
    this.newReview.text = formValue.text!;
    this.newReview.grade = 5;
    
  
    this.reviewService.createReview(this.newReview).subscribe({
      next: (data) => {
        console.log(data);
        location.reload(); 
      },
      error: (e) => {
        console.log('error:', e);
      }
    });
  }


  returnToHome() {
    this.router.navigate([''])
  }

}
