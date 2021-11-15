import { Component, Vue, Inject } from 'vue-property-decorator';

import { IRentalCarCompany } from '@/shared/model/rental-car-company.model';
import RentalCarCompanyService from './rental-car-company.service';

@Component
export default class RentalCarCompanyDetails extends Vue {
  @Inject('rentalCarCompanyService') private rentalCarCompanyService: () => RentalCarCompanyService;
  public rentalCarCompany: IRentalCarCompany = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.rentalCarCompanyId) {
        vm.retrieveRentalCarCompany(to.params.rentalCarCompanyId);
      }
    });
  }

  public retrieveRentalCarCompany(rentalCarCompanyId) {
    this.rentalCarCompanyService()
      .find(rentalCarCompanyId)
      .then(res => {
        this.rentalCarCompany = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
