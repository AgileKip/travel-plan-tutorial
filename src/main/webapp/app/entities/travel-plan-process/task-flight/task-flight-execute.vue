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
              <label class="form-control-label" v-text="$t('travelPlanApp.taskFlight.travelName')" for="task-flight-travelName"
                >Travel Name</label
              >
              <input
                type="text"
                class="form-control"
                name="travelName"
                id="task-flight-travelName"
                readonly
                data-cy="travelName"
                :class="{
                  valid: !$v.taskContext.travelPlanProcess.travelPlan.travelName.$invalid,
                  invalid: $v.taskContext.travelPlanProcess.travelPlan.travelName.$invalid,
                }"
                v-model="$v.taskContext.travelPlanProcess.travelPlan.travelName.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelPlanApp.taskFlight.startDate')" for="task-flight-startDate"
                >Start Date</label
              >
              <b-input-group class="mb-3">
                <b-form-input
                  id="task-flight-startDate"
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
              <label class="form-control-label" v-text="$t('travelPlanApp.taskFlight.endDate')" for="task-flight-endDate">End Date</label>
              <b-input-group class="mb-3">
                <b-form-input
                  id="task-flight-endDate"
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
              <label
                class="form-control-label"
                v-text="$t('travelPlanApp.taskFlight.airlineTicketNumber')"
                for="task-flight-airlineTicketNumber"
                >Airline Ticket Number</label
              >
              <input
                type="text"
                class="form-control"
                name="airlineTicketNumber"
                id="task-flight-airlineTicketNumber"
                data-cy="airlineTicketNumber"
                :class="{
                  valid: !$v.taskContext.travelPlanProcess.travelPlan.airlineTicketNumber.$invalid,
                  invalid: $v.taskContext.travelPlanProcess.travelPlan.airlineTicketNumber.$invalid,
                }"
                v-model="$v.taskContext.travelPlanProcess.travelPlan.airlineTicketNumber.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelPlanApp.taskFlight.airlineCompany')" for="task-flight-airlineCompany"
                >Airline Company</label
              >
              <select
                class="form-control"
                id="task-flight-airlineCompany"
                data-cy="airlineCompany"
                name="airlineCompany"
                v-model="taskContext.travelPlanProcess.travelPlan.airlineCompany"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.travelPlanProcess.travelPlan.airlineCompany &&
                    airlineCompanyOption.id === taskContext.travelPlanProcess.travelPlan.airlineCompany.id
                      ? taskContext.travelPlanProcess.travelPlan.airlineCompany
                      : airlineCompanyOption
                  "
                  v-for="airlineCompanyOption in airlineCompanies"
                  :key="airlineCompanyOption.id"
                >
                  {{ airlineCompanyOption.name }}
                </option>
              </select>
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

<script lang="ts" src="./task-flight-execute.component.ts"></script>
