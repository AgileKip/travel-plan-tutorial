import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IRentalCarCompany } from '@/shared/model/rental-car-company.model';

import RentalCarCompanyService from './rental-car-company.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class RentalCarCompany extends Vue {
  @Inject('rentalCarCompanyService') private rentalCarCompanyService: () => RentalCarCompanyService;
  private removeId: number = null;

  public rentalCarCompanies: IRentalCarCompany[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllRentalCarCompanys();
  }

  public clear(): void {
    this.retrieveAllRentalCarCompanys();
  }

  public retrieveAllRentalCarCompanys(): void {
    this.isFetching = true;

    this.rentalCarCompanyService()
      .retrieve()
      .then(
        res => {
          this.rentalCarCompanies = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IRentalCarCompany): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeRentalCarCompany(): void {
    this.rentalCarCompanyService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('travelPlanApp.rentalCarCompany.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllRentalCarCompanys();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
