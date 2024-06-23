"use client"

import { LOGIN_ERROR } from '@/app/utils/constants'
import { AppRouterInstance } from 'next/dist/shared/lib/app-router-context.shared-runtime';

export const handleErrorClientSide = <T>(router?: AppRouterInstance) => ([data, error]: [T?, string?]): T => {
  if (router && error === LOGIN_ERROR) {
    router.push('/login');
  } else if (error) {
    throw new Error(error);
  }
  return data!;
}
