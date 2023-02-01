package kr.kro.fatcats.allerview.network.exception

import okio.IOException

/**************************************************************************************************
 * Title : 네트워크 단절 에러
 * Description :
 * 서버 통신 요청 시 네트워크 단절된 상태일 경우 발생하는 에러
 *
 *
 * @author startwo09@gmail.com
 * @since 2023/01/25 1:10 PM
 **************************************************************************************************/
class AVNetworkException : IOException("no available network")