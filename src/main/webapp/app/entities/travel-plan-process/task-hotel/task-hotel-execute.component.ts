import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskHotelService from './task-hotel.service';
import { TaskHotelContext } from './task-hotel.model';

const validations: any = {
  taskContext: {
    travelPlanProcess: {
      travelPlan: {
        name: {},
        startDate: {},
        endDate: {},
        hotelName: {},
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
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
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

  public initRelationships(): void {}
}
