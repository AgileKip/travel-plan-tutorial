import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IAirlineCompany } from '@/shared/model/airline-company.model';

import AirlineCompanyService from './airline-company.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class AirlineCompany extends Vue {
  @Inject('airlineCompanyService') private airlineCompanyService: () => AirlineCompanyService;
  private removeId: number = null;

  public airlineCompanies: IAirlineCompany[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllAirlineCompanys();
  }

  public clear(): void {
    this.retrieveAllAirlineCompanys();
  }

  public retrieveAllAirlineCompanys(): void {
    this.isFetching = true;

    this.airlineCompanyService()
      .retrieve()
      .then(
        res => {
          this.airlineCompanies = res.data;
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

  public prepareRemove(instance: IAirlineCompany): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeAirlineCompany(): void {
    this.airlineCompanyService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('travelPlanApp.airlineCompany.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllAirlineCompanys();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
