import { redirect } from 'next/navigation';
import { LOGIN_ERROR } from './constants';
import { Result } from '@/service/AbstractService';

export const handleErrorServerSide = <T>(result: Result<T>): T => {
  if (result.ok) {
    return result.data;
  }

  if (result.error === LOGIN_ERROR) {
    redirect('/login');
  }

  throw new Error(result.error);
}
