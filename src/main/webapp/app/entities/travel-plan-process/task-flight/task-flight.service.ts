import axios from 'axios';
import { TaskFlightContext } from './task-flight.model';

const baseApiUrl = 'api/travel-plan-process/task-flight';

export default class TaskFlightService {
  public loadContext(taskId: number): Promise<TaskFlightContext> {
    return new Promise<TaskFlightContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public claim(taskId: number): Promise<TaskFlightContext> {
    return new Promise<TaskFlightContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}/claim`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public complete(taskFlightContext: TaskFlightContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskFlightContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
