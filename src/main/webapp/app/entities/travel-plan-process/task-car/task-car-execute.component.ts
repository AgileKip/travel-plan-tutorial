import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskCarService from './task-car.service';
import { TaskCarContext } from './task-car.model';

const validations: any = {
  taskContext: {
    travelPlanProcess: {
      travelPlan: {
        name: {},
        startDate: {},
        endDate: {},
        carCompanyName: {},
        carBookingNumber: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskCarExecuteComponent extends Vue {
  private taskCarService: TaskCarService = new TaskCarService();
  private taskContext: TaskCarContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskCarService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskCarService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
