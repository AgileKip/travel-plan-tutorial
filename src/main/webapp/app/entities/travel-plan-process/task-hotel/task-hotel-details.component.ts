import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskHotelService from './task-hotel.service';
import { TaskHotelContext } from './task-hotel.model';

@Component
export default class TaskHotelDetailsComponent extends Vue {
  private taskHotelService: TaskHotelService = new TaskHotelService();
  private taskContext: TaskHotelContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskHotelService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
