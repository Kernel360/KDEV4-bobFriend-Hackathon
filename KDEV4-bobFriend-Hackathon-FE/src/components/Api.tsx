import axios from 'axios'

const Api = axios.create({
  baseURL: 'http://localhost:8080/bobfriend' // 백엔드 주소
})

// 요청 인터셉터: 요청 보낼 때 JWT 자동 추가
Api.interceptors.request.use(
  config => {
    console.log('토큰 생성중...')
    const token = localStorage.getItem('token')?.replace('Bearer ', '').trim() // 'Bearer ' 제거하고 JWT만 사용
    if (token) {
      // secured 쿠키
      console.log(token)
      config.headers.Authorization = `Bearer ${token}` // 인증 헤더 추가
      console.log(config)
    }
    return config
  },
  error => {
    console.log('토큰 생성 중 오류 발생')
    return Promise.reject(error)
  }
)

export default Api
