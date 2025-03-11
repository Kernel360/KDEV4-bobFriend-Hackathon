import { useEffect, useState } from 'react'
import Api from './Api'

export default function UserInfo() {
  const [user, setUser] = useState('')
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(false)

  const handleLogout = (e: React.MouseEvent<HTMLButtonElement>) => {
    localStorage.removeItem('token')
    setIsLoggedIn(false)
    window.location.reload()
  }

  const handleLogin = (e: React.MouseEvent<HTMLButtonElement>) => {
    window.location.href = '/jeedn/login'
  }

  useEffect(() => {
    fetchUser()
  })
  const fetchUser = async () => {
    try {
      const response = await Api.get(
        'http://localhost:8080/bobfriend/users/1',
        {
          headers: {
            'Content-Type': 'application/json'
          }
        }
      )
      console.log(response.data.body)
      setUser(response.data.body)
      setIsLoggedIn(true)
    } catch (error) {
      setIsLoggedIn(false)
    }
  }

  return (
    <div className="mb-6 text-center">
      {isLoggedIn ? (
        <>
          <p className="text-2xl font-semibold text-gray-800">
            안녕하세요, {user}님
          </p>
          <button
            type="button"
            onClick={handleLogout}>
            로그아웃하기
          </button>
        </>
      ) : (
        <button
          type="button"
          onClick={handleLogin}>
          로그인 하러가기
        </button>
      )}
    </div>
  )
}
