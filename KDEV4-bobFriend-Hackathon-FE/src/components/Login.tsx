import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom'
import { ChangeEvent, FormEvent, useState } from 'react'
import Api from './Api'

interface LoginFormData {
  email: string
  password: string
}

export default function Login() {
  const [formData, setFormData] = useState<LoginFormData>({
    email: '',
    password: ''
  })
  const navigate = useNavigate()

  const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { id, value } = e.target

    setFormData(prev => ({
      ...prev,
      [id]: value
    }))
  }

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault()

    const form = new FormData()
    form.append('email', formData.email)
    form.append('password', formData.password)

    try {
      const response = await fetch('http://localhost:8080/bobfriend/auth', {
        method: 'POST',
        body: form,
        headers: {
          // 'Content-Type': 'multipart/form-data'는 설정하지 않아도 자동으로 설정됨.
        }
      })

      if (response.ok) {
        const data = await response.json()
        console.log('로그인 성공')
        console.log(data.token)
        localStorage.setItem('token', data.token)
        navigate('/bobfriend')
      } else {
        throw new Error('로그인 실패! 다시 시도해 주세요.')
      }
    } catch (error) {}
  }

  return (
    <div className="flex min-h-screen items-center justify-center bg-gray-50">
      <div className="w-full max-w-md rounded-lg bg-white p-8 shadow-lg">
        <div
          id="login-header"
          className="mb-6 text-center">
          <h1 className="text-3xl font-bold text-gray-700">로그인하기</h1>
        </div>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <input
              type="text"
              placeholder="이메일을 입력하세요."
              id="email"
              value={formData.email}
              onChange={handleInputChange}
              className="w-full rounded-md border border-gray-300 p-3 focus:ring-2 focus:ring-blue-500 focus:outline-none"
            />
          </div>
          <div className="mb-6">
            <input
              type="password"
              placeholder="비밀번호를 입력하세요."
              id="password"
              value={formData.password}
              onChange={handleInputChange}
              className="w-full rounded-md border border-gray-300 p-3 focus:ring-2 focus:ring-blue-500 focus:outline-none"
            />
          </div>
          <button
            type="submit"
            className="w-full rounded-md bg-blue-500 py-3 font-semibold text-white hover:bg-blue-600 focus:ring-2 focus:ring-blue-500 focus:outline-none">
            로그인하기
          </button>
        </form>
      </div>
    </div>
  )
}
