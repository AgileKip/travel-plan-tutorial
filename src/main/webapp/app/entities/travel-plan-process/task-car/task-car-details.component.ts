import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskCarService from './task-car.service';
import { TaskCarContext } from './task-car.model';

@Component
export default class TaskCarDetailsComponent extends Vue {
  private taskCarService: TaskCarService = new TaskCarService();
  private taskContext: TaskCarContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskCarService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
