"use client"

import { LOGIN_ERROR } from '@/app/utils/constants'
import { Result } from '@/service/AbstractService';
import { AppRouterInstance } from 'next/dist/shared/lib/app-router-context.shared-runtime';

export const handleErrorClientSide = <T>(router?: AppRouterInstance) => (result: Result<T>): T => {
  if (result.ok) {
    return result.data;
  }

  if (router && result.error === LOGIN_ERROR) {
    router.push('/login');
    return null as any;
  }

  throw new Error(result.error);
}
