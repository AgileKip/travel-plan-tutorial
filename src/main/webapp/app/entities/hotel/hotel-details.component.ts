import { Component, Vue, Inject } from 'vue-property-decorator';

import { IHotel } from '@/shared/model/hotel.model';
import HotelService from './hotel.service';

@Component
export default class HotelDetails extends Vue {
  @Inject('hotelService') private hotelService: () => HotelService;
  public hotel: IHotel = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.hotelId) {
        vm.retrieveHotel(to.params.hotelId);
      }
    });
  }

  public retrieveHotel(hotelId) {
    this.hotelService()
      .find(hotelId)
      .then(res => {
        this.hotel = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
