import axios from 'axios';
import { TaskCarContext } from './task-car.model';

const baseApiUrl = 'api/travel-plan-process/task-car';

export default class TaskCarService {
  public loadContext(taskId: number): Promise<TaskCarContext> {
    return new Promise<TaskCarContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskCarContext> {
    return new Promise<TaskCarContext>((resolve, reject) => {
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

  public complete(taskCarContext: TaskCarContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskCarContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
