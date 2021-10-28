import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskFlightService from './task-flight.service';
import { TaskFlightContext } from './task-flight.model';

@Component
export default class TaskFlightDetailsComponent extends Vue {
  private taskFlightService: TaskFlightService = new TaskFlightService();
  private taskContext: TaskFlightContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskFlightService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
