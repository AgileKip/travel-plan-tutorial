<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="travelPlanApp.travelPlanStartForm.home.createOrEditLabel"
          data-cy="TravelPlanStartFormCreateUpdateHeading"
          v-text="$t('travelPlanApp.travelPlanStartForm.home.createOrEditLabel')"
        >
          Create or edit a TravelPlanStartForm
        </h2>
        <div v-if="travelPlanProcess.processInstance">
          <akip-show-process-definition :processDefinition="travelPlanProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="travelPlanProcess.travelPlan">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('travelPlanApp.travelPlanStartForm.travelName')"
                    for="travel-plan-start-form-travelName"
                    >Travel Name</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="travelName"
                    id="travel-plan-start-form-travelName"
                    data-cy="travelName"
                    :class="{
                      valid: !$v.travelPlanProcess.travelPlan.travelName.$invalid,
                      invalid: $v.travelPlanProcess.travelPlan.travelName.$invalid,
                    }"
                    v-model="$v.travelPlanProcess.travelPlan.travelName.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('travelPlanApp.travelPlanStartForm.userName')"
                    for="travel-plan-start-form-userName"
                    >User Name</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="userName"
                    id="travel-plan-start-form-userName"
                    data-cy="userName"
                    :class="{
                      valid: !$v.travelPlanProcess.travelPlan.userName.$invalid,
                      invalid: $v.travelPlanProcess.travelPlan.userName.$invalid,
                    }"
                    v-model="$v.travelPlanProcess.travelPlan.userName.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('travelPlanApp.travelPlanStartForm.userEmail')"
                    for="travel-plan-start-form-userEmail"
                    >User Email</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="userEmail"
                    id="travel-plan-start-form-userEmail"
                    data-cy="userEmail"
                    :class="{
                      valid: !$v.travelPlanProcess.travelPlan.userEmail.$invalid,
                      invalid: $v.travelPlanProcess.travelPlan.userEmail.$invalid,
                    }"
                    v-model="$v.travelPlanProcess.travelPlan.userEmail.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('travelPlanApp.travelPlanStartForm.startDate')"
                    for="travel-plan-start-form-startDate"
                    >Start Date</label
                  >
                  <b-input-group class="mb-3">
                    <b-input-group-prepend>
                      <b-form-datepicker
                        aria-controls="travel-plan-start-form-startDate"
                        v-model="$v.travelPlanProcess.travelPlan.startDate.$model"
                        name="startDate"
                        class="form-control"
                        :locale="currentLanguage"
                        button-only
                        today-button
                        reset-button
                        close-button
                      >
                      </b-form-datepicker>
                    </b-input-group-prepend>
                    <b-form-input
                      id="travel-plan-start-form-startDate"
                      data-cy="startDate"
                      type="text"
                      class="form-control"
                      name="startDate"
                      :class="{
                        valid: !$v.travelPlanProcess.travelPlan.startDate.$invalid,
                        invalid: $v.travelPlanProcess.travelPlan.startDate.$invalid,
                      }"
                      v-model="$v.travelPlanProcess.travelPlan.startDate.$model"
                    />
                  </b-input-group>
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('travelPlanApp.travelPlanStartForm.endDate')"
                    for="travel-plan-start-form-endDate"
                    >End Date</label
                  >
                  <b-input-group class="mb-3">
                    <b-input-group-prepend>
                      <b-form-datepicker
                        aria-controls="travel-plan-start-form-endDate"
                        v-model="$v.travelPlanProcess.travelPlan.endDate.$model"
                        name="endDate"
                        class="form-control"
                        :locale="currentLanguage"
                        button-only
                        today-button
                        reset-button
                        close-button
                      >
                      </b-form-datepicker>
                    </b-input-group-prepend>
                    <b-form-input
                      id="travel-plan-start-form-endDate"
                      data-cy="endDate"
                      type="text"
                      class="form-control"
                      name="endDate"
                      :class="{
                        valid: !$v.travelPlanProcess.travelPlan.endDate.$invalid,
                        invalid: $v.travelPlanProcess.travelPlan.endDate.$invalid,
                      }"
                      v-model="$v.travelPlanProcess.travelPlan.endDate.$model"
                    />
                  </b-input-group>
                </div>
              </div>
            </template>
          </akip-show-process-definition>
          <br />
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.travelPlanProcess.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./travel-plan-start-form-init.component.ts"></script>
