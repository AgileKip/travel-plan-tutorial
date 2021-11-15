import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IHotel } from '@/shared/model/hotel.model';

import HotelService from './hotel.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Hotel extends Vue {
  @Inject('hotelService') private hotelService: () => HotelService;
  private removeId: number = null;

  public hotels: IHotel[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllHotels();
  }

  public clear(): void {
    this.retrieveAllHotels();
  }

  public retrieveAllHotels(): void {
    this.isFetching = true;

    this.hotelService()
      .retrieve()
      .then(
        res => {
          this.hotels = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IHotel): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeHotel(): void {
    this.hotelService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('travelPlanApp.hotel.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllHotels();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
