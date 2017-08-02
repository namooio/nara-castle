
import { ajax } from 'nara-core';
import { dialogUtil } from 'nara-react';


ajax.setErrorCallback((errorCode) => {
  //
  if (errorCode === 401) {
    dialogUtil.alert('Time has expired and you are logged out.', () => {
      window.location.pathname = '/';
    });
    console.log('[Pav] Pavilion reload cause error code : 401');
  }
});

export default {
  //
  findCastellans:     (lastCastellanId, limit)            => ajax.getJSON(`/castle-api/castellans?lastCastellanId=${lastCastellanId}&limit=${limit}`),
  //findCastellansPage: (page, limit) => ajax.getJSON(`/castle-api/castellans-page?page=${page}&limit=${limit}`),

  findEnrollments:    (castellanId) => ajax.getJSON(`/castle-api/castellans/${castellanId}/enrollments`),
};
