import { useEffect, useState } from 'react'
import Api from './Api'

export default function Header({ setUser }: { setUser: (info: any) => void }) {
  // const [user, setUser] = useState<User>({
  //   userId: 0,
  //   name: '',
  //   email: ''
  // })
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(false)
  const [isMenuOpen, setIsMenuOpen] = useState(false) // 메뉴 상태 관리

  const toggleMenu = () => {
    setIsMenuOpen(prev => !prev)
  }

  // const [user, setUser] = useState('')
  const handleLogout = (e: React.MouseEvent<HTMLButtonElement>) => {
    localStorage.removeItem('token')
    setIsLoggedIn(false)
    console.log(e.target)
    window.location.reload()
  }

  const handleLogin = (e: React.MouseEvent<HTMLButtonElement>) => {
    window.location.href = '/bobfriend/auth'
    console.log(e.target)
  }

  useEffect(() => {
    fetchUser()
  }, [])

  const fetchUser = async () => {
    try {
      const response = await Api.get(
        `http://175.106.98.84:8080/bobfriend/users/validate`,
        {
          headers: {
            'Content-Type': 'application/json'
          }
        }
      )
      console.log(response.data)
      const data = response.data

      if (localStorage.getItem('token') !== null) {
        setIsLoggedIn(true)
        setUser({
          userId: data.userId,
          name: data.name,
          email: data.email
        })
        //setUser(localStorage.getItem('username') + '')
      } else {
        setIsLoggedIn(false)
      }
    } catch (error) {
      setIsLoggedIn(false)
    }
  }

  return (
    <div className="relative">
      {/* 로고 텍스트 좌상단 배치 */}
      <div className="absolute top-5 left-5 text-2xl font-semibold text-gray-800">
        <a href="/bobfriend">BobFriends</a>
      </div>

      {/* 메뉴 버튼 우상단 */}
      <button
        onClick={toggleMenu}
        className="absolute top-6 right-10 text-lg text-gray-800">
        메뉴 {/* 메뉴 아이콘 */}
      </button>

      {/* 슬라이딩 메뉴 */}
      <div
        className={`fixed top-0 right-0 z-50 h-full bg-black p-5 text-white transition-transform ${
          isMenuOpen ? 'transform-none' : 'translate-x-full transform'
        }`}
        style={{ width: '400px' }}>
        <h2 className="mt-5 mb-5 text-2xl font-bold">메뉴</h2>
        <ul>
          <li className="mb-4">
            <a href="/bobfriend">홈</a>
          </li>
          <li className="mb-4">
            <a href="/bobfriend/places">밥집</a>
          </li>
        </ul>
        <button
          onClick={toggleMenu}
          className="absolute top-10 right-10 text-2xl">
          ✖
        </button>
      </div>

      {/* 로그인/로그아웃 버튼 중앙 */}
      <div className="mb-6 text-center">
        {isLoggedIn ? (
          <>
            <p className="mt-5 text-2xl font-semibold text-gray-800">
              안녕하세요, {localStorage.getItem('userName')}님
            </p>
            <button
              type="button"
              onClick={handleLogout}
              className="mt-4 rounded bg-gray-800 px-4 py-2 text-white">
              로그아웃하기
            </button>
          </>
        ) : (
          <button
            type="button"
            onClick={handleLogin}
            className="mt-8 rounded bg-black px-4 py-2 text-white">
            로그인 하러가기
          </button>
        )}
      </div>
    </div>
  )
}
