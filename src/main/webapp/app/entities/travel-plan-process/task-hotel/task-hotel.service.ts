import axios from 'axios';
import { TaskHotelContext } from './task-hotel.model';

const baseApiUrl = 'api/travel-plan-process/task-hotel';

export default class TaskHotelService {
  public loadContext(taskId: number): Promise<TaskHotelContext> {
    return new Promise<TaskHotelContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskHotelContext> {
    return new Promise<TaskHotelContext>((resolve, reject) => {
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

  public complete(taskHotelContext: TaskHotelContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskHotelContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
