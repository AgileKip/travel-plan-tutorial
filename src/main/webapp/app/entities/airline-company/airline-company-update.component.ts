import { Component, Vue, Inject } from 'vue-property-decorator';

import { IAirlineCompany, AirlineCompany } from '@/shared/model/airline-company.model';
import AirlineCompanyService from './airline-company.service';

const validations: any = {
  airlineCompany: {
    name: {},
    website: {},
    email: {},
  },
};

@Component({
  validations,
})
export default class AirlineCompanyUpdate extends Vue {
  @Inject('airlineCompanyService') private airlineCompanyService: () => AirlineCompanyService;
  public airlineCompany: IAirlineCompany = new AirlineCompany();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.airlineCompanyId) {
        vm.retrieveAirlineCompany(to.params.airlineCompanyId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.airlineCompany.id) {
      this.airlineCompanyService()
        .update(this.airlineCompany)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('travelPlanApp.airlineCompany.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.airlineCompanyService()
        .create(this.airlineCompany)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('travelPlanApp.airlineCompany.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  public retrieveAirlineCompany(airlineCompanyId): void {
    this.airlineCompanyService()
      .find(airlineCompanyId)
      .then(res => {
        this.airlineCompany = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
