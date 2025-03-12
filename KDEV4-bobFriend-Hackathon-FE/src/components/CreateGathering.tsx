import { ChangeEvent, useState } from 'react'
import { User } from './interfaces/types'
import { Gathering } from './interfaces/types'
import Api from './Api'

export default function CreateGathering({ user }: { user: User }) {
  interface GatheringFormData {
    title: string
    content: string
    gathering_at: string
    closing_at: string
    max_participant: number
    talk_thema: string
    talk_flag: string
    user_id: number
    user_name: string
    place_id: string
  }

  const [isOpen, setIsOpen] = useState(true) // 모임 생성 창 열고 닫기
  const [title, setTitle] = useState('')
  const [content, setContent] = useState('')
  const [gatheringAt, setGatheringAt] = useState('')
  const [closingAt, setClosingAt] = useState('')
  const [maxParticipant, setMaxParticipant] = useState(1)
  const [talkThema, setTalkThema] = useState('')
  const [talkFlag, setTalkFlag] = useState<string | null>('I')

  const [formData, setFormData] = useState<GatheringFormData>({
    title: '',
    content: '',
    gathering_at: '',
    closing_at: '',
    max_participant: 0,
    talk_thema: '',
    talk_flag: 'I',
    user_id: user.userId,
    user_name: user.name,
    place_id: ''
  })

  const handleInputChange = (
    e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const id = e.target.id
    const value = e.target.value

    const prev = formData

    const updatedFormData = {
      ...prev,
      [id]: value
    }
    setFormData(updatedFormData)
    console.log(user.userId)
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()

    const form = {
      title: formData.title,
      content: formData.content,
      gathering_at: formData.gathering_at,
      closing_at: formData.closing_at,
      max_participant: formData.max_participant,
      talk_thema: formData.talk_thema || null,
      talk_flag: formData.talk_flag || null,
      user_id: user.userId,
      user_name: user.name,
      place_id: formData.place_id
    }

    try {
      const response = await Api.post(
        'http://localhost:8080/bobfriend/gatherings',
        form,
        {
          headers: {
            'Content-Type': 'application/json'
          }
        }
      )
      // 응답 처리
      console.log('게시물 생성 성공', response.data)
      window.location.reload()
    } catch (error) {
      console.error('게시물 생성 실패:', error)
    }
  }

  return (
    <div className="relative mx-auto max-w-4xl rounded-lg bg-white p-6 shadow-lg">
      <h2 className="mb-4 text-2xl font-bold text-gray-700">
        밥친구 모으기 🍔
      </h2>

      {/* 열고 닫기 버튼 */}
      <button
        onClick={() => setIsOpen(!isOpen)}
        className="text-m absolute top-5 right-5 rounded-md bg-black p-2 text-white">
        {isOpen ? '닫기' : '열기'}
      </button>

      {isOpen && (
        <form
          onSubmit={handleSubmit}
          className="mt-4 space-y-4">
          <div>
            <label
              htmlFor="title"
              className="block text-gray-700">
              모임 제목
            </label>
            <input
              type="text"
              id="title"
              value={formData.title}
              onChange={handleInputChange}
              className="w-full rounded-md border border-gray-300 p-2"
              required
            />
          </div>

          <div>
            <label
              htmlFor="content"
              className="block text-gray-700">
              모임 내용
            </label>
            <textarea
              id="content"
              value={formData.content}
              onChange={handleInputChange}
              className="w-full rounded-md border border-gray-300 p-2"
              required
            />
          </div>

          <div>
            <label
              htmlFor="place_id"
              className="block text-gray-700">
              장소
            </label>
            <input
              id="place_id"
              value={formData.place_id}
              onChange={handleInputChange}
              className="w-full rounded-md border border-gray-300 p-2"
              required
            />
          </div>

          {/* 모임 일시와 종료 일시를 한 줄에 배치 */}
          <div className="flex space-x-4">
            <div className="flex-1">
              <label
                htmlFor="gathering_at"
                className="block text-gray-700">
                모임 일시
              </label>
              <input
                type="text"
                id="gathering_at"
                value={formData.gathering_at}
                onChange={handleInputChange}
                className="w-full rounded-md border border-gray-300 p-2"
                required
              />
            </div>
            <div className="flex-1">
              <label
                htmlFor="closing_at"
                className="block text-gray-700">
                모임 종료 일시
              </label>
              <input
                type="text"
                id="closing_at"
                value={formData.closing_at}
                onChange={handleInputChange}
                className="w-full rounded-md border border-gray-300 p-2"
                required
              />
            </div>
          </div>

          <div className="flex space-x-4">
            <div className="flex-1">
              <label
                htmlFor="talk_flag"
                className="block text-gray-700">
                이런 분들 환영해요
              </label>
              <div className="flex space-x-4">
                <button
                  type="button"
                  onClick={() =>
                    setFormData(prev => ({ ...prev, talk_flag: 'I' }))
                  }
                  className={`rounded-md border px-4 py-2 transition duration-100 hover:scale-105 ${formData.talk_flag === 'I' ? 'bg-black text-white' : 'bg-gray-200'}`}>
                  내향적
                </button>
                <button
                  type="button"
                  onClick={() =>
                    setFormData(prev => ({ ...prev, talk_flag: 'E' }))
                  }
                  className={`rounded-md border px-4 py-2 transition duration-100 hover:scale-105 ${formData.talk_flag === 'E' ? 'bg-black text-white' : 'bg-gray-200'}`}>
                  외향적
                </button>
                <button
                  type="button"
                  onClick={() =>
                    setFormData(prev => ({ ...prev, talk_flag: 'S' }))
                  }
                  className={`rounded-md border px-4 py-2 transition duration-100 hover:scale-105 ${formData.talk_flag === 'S' ? 'bg-black text-white' : 'bg-gray-200'}`}>
                  소식좌
                </button>
                <button
                  type="button"
                  onClick={() =>
                    setFormData(prev => ({ ...prev, talk_flag: 'B' }))
                  }
                  className={`rounded-md border px-4 py-2 transition duration-100 hover:scale-105 ${formData.talk_flag === 'B' ? 'bg-black text-white' : 'bg-gray-200'}`}>
                  푸드 파이터
                </button>
              </div>
            </div>

            {/* 최대 참가 인원은 1/3 너비로 설정 */}
            <div className="w-1/3">
              <label
                htmlFor="maxParticipant"
                className="block text-gray-700">
                최대 참가 인원
              </label>
              <input
                type="number"
                id="max_participant"
                value={formData.max_participant}
                onChange={e =>
                  setFormData(prev => ({
                    ...prev,
                    max_participant: Math.max(0, Number(e.target.value))
                  }))
                }
                className="w-full rounded-md border border-gray-300 p-2"
                min="0"
                required
              />
            </div>
          </div>

          <div>
            <label
              htmlFor="talkThema"
              className="block text-gray-700">
              오늘은 어떤 대화를...(선택)
            </label>
            <input
              type="text"
              id="talk_thema"
              value={formData.talk_thema}
              onChange={handleInputChange}
              className="w-full rounded-md border border-gray-300 p-2"
            />
          </div>

          <button
            type="submit"
            className="mt-4 w-full rounded-md bg-black p-2 text-white">
            모임 생성하기
          </button>
        </form>
      )}
    </div>
  )
}
