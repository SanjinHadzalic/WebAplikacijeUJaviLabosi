import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, NumberValueAccessor, ReactiveFormsModule, Validators } from '@angular/forms';
import { Review } from '../../interfaces/review';
import { ReviewService } from '../../services/review.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Vozilo } from '../../interfaces/vozilo';
import { VoziloService } from '../../services/vozilo.service';
import { JwtDecoderService } from '../../services/jwt-decoder.service';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'app-review-details',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, TranslateModule],
  templateUrl: './review-details.component.html',
  styleUrl: './review-details.component.css',
  providers: [ReviewService, VoziloService]
})
export class ReviewDetailsComponent {
  id!: number;
  review!: Review;
  reviewForm!: FormGroup
  selectedVoziloId!: number;
  vozila!: Vozilo[];
  decoded!: any;

  constructor(
    private reviewService: ReviewService,
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private voziloService: VoziloService,
    private decodeService: JwtDecoderService
  ){}

  ngOnInit(){
    this.getVozila()
    this.id = +this.route.snapshot.params['id'];
    this.reviewForm = this.formBuilder.group({
      title: ['', Validators.required],
      text: ['', Validators.required],
      grade: ['', Validators. required],
      vozilo: ['', Validators.required]
    })

    this.reviewService.getReviewById(this.route.snapshot.params['id']).subscribe((review)=>{
      this.review = review;
      this.reviewForm.patchValue({
        title: this.review.title,
        text: this.review.text,
        grade: this.review.grade
      })
    })
  }

  private getVozila() {
    this.voziloService.getVoziloList().subscribe(voz=>{
      this.vozila = voz;
    })
  }


  updateReview(): void {
    const jwtToken = localStorage.getItem('JWT')
    this.decoded = this.decodeService.decodeToken(jwtToken ?? "emptyToken")

    const updatedReview: Review = {
      id: this.id,
      title: this.reviewForm.value.title,
      text: this.reviewForm.value.text,
      grade: this.reviewForm.value.grade,
      vozilo: this.vozila[this.selectedVoziloId - 1],
      user: this.decoded.sub
    }

    this.reviewService.updateReview(this.id,updatedReview).subscribe(()=>{
      this.router.navigate(['/review'])
    })
  }

  return(){
    this.router.navigate(['/review'])
  }
}
