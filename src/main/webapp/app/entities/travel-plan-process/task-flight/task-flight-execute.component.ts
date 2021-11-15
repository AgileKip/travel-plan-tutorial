import { Component, Vue, Inject } from 'vue-property-decorator';

import AirlineCompanyService from '@/entities/airline-company/airline-company.service';
import { IAirlineCompany } from '@/shared/model/airline-company.model';

import TaskFlightService from './task-flight.service';
import { TaskFlightContext } from './task-flight.model';

const validations: any = {
  taskContext: {
    travelPlanProcess: {
      travelPlan: {
        travelName: {},
        startDate: {},
        endDate: {},
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

  @Inject('airlineCompanyService') private airlineCompanyService: () => AirlineCompanyService;

  public airlineCompanies: IAirlineCompany[] = [];
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

  public initRelationships(): void {
    this.airlineCompanyService()
      .retrieve()
      .then(res => {
        this.airlineCompanies = res.data;
      });
  }
}
