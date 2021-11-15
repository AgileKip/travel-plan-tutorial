import { Component, Vue, Inject } from 'vue-property-decorator';

import HotelService from '@/entities/hotel/hotel.service';
import { IHotel } from '@/shared/model/hotel.model';

import TaskHotelService from './task-hotel.service';
import { TaskHotelContext } from './task-hotel.model';

const validations: any = {
  taskContext: {
    travelPlanProcess: {
      travelPlan: {
        travelName: {},
        startDate: {},
        endDate: {},
        hotelBookingNumber: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskHotelExecuteComponent extends Vue {
  private taskHotelService: TaskHotelService = new TaskHotelService();
  private taskContext: TaskHotelContext = {};

  @Inject('hotelService') private hotelService: () => HotelService;

  public hotels: IHotel[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
      vm.initRelationships();
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskHotelService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskHotelService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.hotelService()
      .retrieve()
      .then(res => {
        this.hotels = res.data;
      });
  }
}
