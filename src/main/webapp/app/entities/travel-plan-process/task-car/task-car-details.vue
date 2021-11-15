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
              <label class="form-control-label" v-text="$t('travelPlanApp.taskCar.travelName')">travelName</label>
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
              <label class="form-control-label" v-text="$t('travelPlanApp.taskCar.startDate')">startDate</label>
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
              <label class="form-control-label" v-text="$t('travelPlanApp.taskCar.endDate')">endDate</label>
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
              <label class="form-control-label" v-text="$t('travelPlanApp.taskCar.carBookingNumber')">carBookingNumber</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="carBookingNumber"
                id="travel-plan-carBookingNumber"
                data-cy="carBookingNumber"
                v-model="taskContext.travelPlanProcess.travelPlan.carBookingNumber"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelPlanApp.taskCar.rentalCarCompany')" for="task-car-rentalCarCompany"
                >Rental Car Company</label
              >
              <input
                v-if="taskContext.travelPlanProcess.travelPlan.rentalCarCompany"
                readonly
                type="text"
                class="form-control"
                name="rentalCarCompany"
                id="travel-plan-rentalCarCompany"
                data-cy="rentalCarCompany"
                :value="taskContext.travelPlanProcess.travelPlan.rentalCarCompany.name"
              />
              <input
                v-else
                readonly
                type="text"
                class="form-control"
                name="rentalCarCompany"
                id="travel-plan-rentalCarCompany"
                data-cy="rentalCarCompany"
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
          :to="`/process-definition/TravelPlanProcess/task/TaskCar/${taskContext.taskInstance.id}/execute`"
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

<script lang="ts" src="./task-car-details.component.ts"></script>
