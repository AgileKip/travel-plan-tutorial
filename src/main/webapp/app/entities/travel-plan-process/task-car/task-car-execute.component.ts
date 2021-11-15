import { Component, Vue, Inject } from 'vue-property-decorator';

import RentalCarCompanyService from '@/entities/rental-car-company/rental-car-company.service';
import { IRentalCarCompany } from '@/shared/model/rental-car-company.model';

import TaskCarService from './task-car.service';
import { TaskCarContext } from './task-car.model';

const validations: any = {
  taskContext: {
    travelPlanProcess: {
      travelPlan: {
        travelName: {},
        startDate: {},
        endDate: {},
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

  @Inject('rentalCarCompanyService') private rentalCarCompanyService: () => RentalCarCompanyService;

  public rentalCarCompanies: IRentalCarCompany[] = [];
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

  public initRelationships(): void {
    this.rentalCarCompanyService()
      .retrieve()
      .then(res => {
        this.rentalCarCompanies = res.data;
      });
  }
}
