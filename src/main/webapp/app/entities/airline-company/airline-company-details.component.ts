import { Component, Vue, Inject } from 'vue-property-decorator';

import { IAirlineCompany } from '@/shared/model/airline-company.model';
import AirlineCompanyService from './airline-company.service';

@Component
export default class AirlineCompanyDetails extends Vue {
  @Inject('airlineCompanyService') private airlineCompanyService: () => AirlineCompanyService;
  public airlineCompany: IAirlineCompany = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.airlineCompanyId) {
        vm.retrieveAirlineCompany(to.params.airlineCompanyId);
      }
    });
  }

  public retrieveAirlineCompany(airlineCompanyId) {
    this.airlineCompanyService()
      .find(airlineCompanyId)
      .then(res => {
        this.airlineCompany = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
