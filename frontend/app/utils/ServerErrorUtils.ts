import { redirect } from 'next/navigation';
import { LOGIN_ERROR } from './constants';

export const handleErrorServerSide = <T>([data, error]: [T?, string?]): T => {
  if (error === LOGIN_ERROR) {
    redirect('/login');
  } else if (error) {
    throw new Error(error);
  }
  return data!;
}
