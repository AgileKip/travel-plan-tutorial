<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('travelPlanApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelPlanApp.taskCar.name')" for="task-car-name">Name</label>
              <input
                type="text"
                class="form-control"
                name="name"
                id="task-car-name"
                readonly
                data-cy="name"
                :class="{
                  valid: !$v.taskContext.travelPlanProcess.travelPlan.name.$invalid,
                  invalid: $v.taskContext.travelPlanProcess.travelPlan.name.$invalid,
                }"
                v-model="$v.taskContext.travelPlanProcess.travelPlan.name.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelPlanApp.taskCar.startDate')" for="task-car-startDate">Start Date</label>
              <b-input-group class="mb-3">
                <b-form-input
                  id="task-car-startDate"
                  readonly
                  data-cy="startDate"
                  type="text"
                  class="form-control"
                  name="startDate"
                  :class="{
                    valid: !$v.taskContext.travelPlanProcess.travelPlan.startDate.$invalid,
                    invalid: $v.taskContext.travelPlanProcess.travelPlan.startDate.$invalid,
                  }"
                  v-model="$v.taskContext.travelPlanProcess.travelPlan.startDate.$model"
                />
              </b-input-group>
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelPlanApp.taskCar.endDate')" for="task-car-endDate">End Date</label>
              <b-input-group class="mb-3">
                <b-form-input
                  id="task-car-endDate"
                  readonly
                  data-cy="endDate"
                  type="text"
                  class="form-control"
                  name="endDate"
                  :class="{
                    valid: !$v.taskContext.travelPlanProcess.travelPlan.endDate.$invalid,
                    invalid: $v.taskContext.travelPlanProcess.travelPlan.endDate.$invalid,
                  }"
                  v-model="$v.taskContext.travelPlanProcess.travelPlan.endDate.$model"
                />
              </b-input-group>
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelPlanApp.taskCar.carCompanyName')" for="task-car-carCompanyName"
                >Car Company Name</label
              >
              <input
                type="text"
                class="form-control"
                name="carCompanyName"
                id="task-car-carCompanyName"
                data-cy="carCompanyName"
                :class="{
                  valid: !$v.taskContext.travelPlanProcess.travelPlan.carCompanyName.$invalid,
                  invalid: $v.taskContext.travelPlanProcess.travelPlan.carCompanyName.$invalid,
                }"
                v-model="$v.taskContext.travelPlanProcess.travelPlan.carCompanyName.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelPlanApp.taskCar.carBookingNumber')" for="task-car-carBookingNumber"
                >Car Booking Number</label
              >
              <input
                type="text"
                class="form-control"
                name="carBookingNumber"
                id="task-car-carBookingNumber"
                data-cy="carBookingNumber"
                :class="{
                  valid: !$v.taskContext.travelPlanProcess.travelPlan.carBookingNumber.$invalid,
                  invalid: $v.taskContext.travelPlanProcess.travelPlan.carBookingNumber.$invalid,
                }"
                v-model="$v.taskContext.travelPlanProcess.travelPlan.carBookingNumber.$model"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <button type="submit" v-on:click.prevent="complete()" class="btn btn-success" data-cy="complete">
          <font-awesome-icon icon="check-circle"></font-awesome-icon>&nbsp;Complete
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-car-execute.component.ts"></script>
