import { Component, Vue, Inject } from 'vue-property-decorator';

import { IHotel, Hotel } from '@/shared/model/hotel.model';
import HotelService from './hotel.service';

const validations: any = {
  hotel: {
    name: {},
    website: {},
    email: {},
  },
};

@Component({
  validations,
})
export default class HotelUpdate extends Vue {
  @Inject('hotelService') private hotelService: () => HotelService;
  public hotel: IHotel = new Hotel();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.hotelId) {
        vm.retrieveHotel(to.params.hotelId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.hotel.id) {
      this.hotelService()
        .update(this.hotel)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('travelPlanApp.hotel.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.hotelService()
        .create(this.hotel)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('travelPlanApp.hotel.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  public retrieveHotel(hotelId): void {
    this.hotelService()
      .find(hotelId)
      .then(res => {
        this.hotel = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
