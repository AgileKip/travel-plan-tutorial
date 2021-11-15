import { Component, Vue, Inject } from 'vue-property-decorator';

import { IRentalCarCompany, RentalCarCompany } from '@/shared/model/rental-car-company.model';
import RentalCarCompanyService from './rental-car-company.service';

const validations: any = {
  rentalCarCompany: {
    name: {},
    website: {},
    email: {},
  },
};

@Component({
  validations,
})
export default class RentalCarCompanyUpdate extends Vue {
  @Inject('rentalCarCompanyService') private rentalCarCompanyService: () => RentalCarCompanyService;
  public rentalCarCompany: IRentalCarCompany = new RentalCarCompany();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.rentalCarCompanyId) {
        vm.retrieveRentalCarCompany(to.params.rentalCarCompanyId);
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
    if (this.rentalCarCompany.id) {
      this.rentalCarCompanyService()
        .update(this.rentalCarCompany)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('travelPlanApp.rentalCarCompany.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.rentalCarCompanyService()
        .create(this.rentalCarCompany)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('travelPlanApp.rentalCarCompany.created', { param: param.id });
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

  public retrieveRentalCarCompany(rentalCarCompanyId): void {
    this.rentalCarCompanyService()
      .find(rentalCarCompanyId)
      .then(res => {
        this.rentalCarCompany = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
