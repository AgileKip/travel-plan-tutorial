import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskFlightService from './task-flight.service';
import { TaskFlightContext } from './task-flight.model';

const validations: any = {
  taskContext: {
    travelPlanProcess: {
      travelPlan: {
        name: {},
        startDate: {},
        endDate: {},
        airlineCompanyName: {},
        airlineTicketNumber: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskFlightExecuteComponent extends Vue {
  private taskFlightService: TaskFlightService = new TaskFlightService();
  private taskContext: TaskFlightContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskFlightService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskFlightService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
