<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <h2 id="page-heading" data-cy="TaskInstanceHeading">
        <span v-text="$t('travelPlanApp.taskInstance.details.title')" id="task-instance-heading">Task Details</span>
      </h2>
      <div v-if="taskContext.taskInstance">
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelPlanApp.taskFlight.travelName')">travelName</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="travelName"
                id="travel-plan-travelName"
                data-cy="travelName"
                v-model="taskContext.travelPlanProcess.travelPlan.travelName"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelPlanApp.taskFlight.startDate')">startDate</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="startDate"
                id="travel-plan-startDate"
                data-cy="startDate"
                v-model="taskContext.travelPlanProcess.travelPlan.startDate"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelPlanApp.taskFlight.endDate')">endDate</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="endDate"
                id="travel-plan-endDate"
                data-cy="endDate"
                v-model="taskContext.travelPlanProcess.travelPlan.endDate"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelPlanApp.taskFlight.airlineTicketNumber')">airlineTicketNumber</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="airlineTicketNumber"
                id="travel-plan-airlineTicketNumber"
                data-cy="airlineTicketNumber"
                v-model="taskContext.travelPlanProcess.travelPlan.airlineTicketNumber"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelPlanApp.taskFlight.airlineCompany')" for="task-flight-airlineCompany"
                >Airline Company</label
              >
              <input
                v-if="taskContext.travelPlanProcess.travelPlan.airlineCompany"
                readonly
                type="text"
                class="form-control"
                name="airlineCompany"
                id="travel-plan-airlineCompany"
                data-cy="airlineCompany"
                :value="taskContext.travelPlanProcess.travelPlan.airlineCompany.name"
              />
              <input
                v-else
                readonly
                type="text"
                class="form-control"
                name="airlineCompany"
                id="travel-plan-airlineCompany"
                data-cy="airlineCompany"
                value=""
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/TravelPlanProcess/task/TaskFlight/${taskContext.taskInstance.id}/execute`"
          tag="button"
          class="btn btn-primary"
          data-cy="entityDetailsButton"
        >
          <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;Execute
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-flight-details.component.ts"></script>
