import { useState } from 'react'

function App() {
  return (
    <div className="min-h-screen flex flex-col items-center justify-center p-6 bg-[radial-gradient(ellipse_at_top,_var(--tw-gradient-stops))] from-slate-900 via-slate-950 to-black">
      <div className="max-w-4xl w-full">
        {/* Header Section */}
        <header className="flex flex-col items-center mb-16 animate-in fade-in slide-in-from-bottom-4 duration-1000">
          <div className="w-20 h-20 mb-6 rounded-2xl bg-gradient-to-br from-indigo-500 to-purple-600 flex items-center justify-center shadow-2xl shadow-indigo-500/20">
            <span className="text-4xl font-bold text-white">T</span>
          </div>
          <h1 className="text-6xl font-black tracking-tight text-white mb-4 bg-clip-text text-transparent bg-gradient-to-r from-white to-slate-400">
            TENACIOUS
          </h1>
          <p className="text-xl text-slate-400 font-medium">
            Hệ thống quản trị nhân sự thế hệ mới
          </p>
        </header>

        {/* Features Preview */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-12">
          {[
            { title: 'Quản lý Đa công ty', desc: 'Mô hình Multi-tenant an toàn' },
            { title: 'Chấm Công AI', desc: 'QR động & Định vị GPS' },
            { title: 'Tính Lương', desc: 'Tự động hóa hoàn toàn 100%' }
          ].map((item, i) => (
            <div
              key={i}
              className="p-6 rounded-2xl border border-slate-800 bg-slate-900/50 backdrop-blur-sm hover:border-indigo-500/50 transition-all cursor-pointer group"
            >
              <h3 className="text-lg font-bold text-white mb-2 group-hover:text-indigo-400 transition-colors">
                {item.title}
              </h3>
              <p className="text-slate-400 text-sm">
                {item.desc}
              </p>
            </div>
          ))}
        </div>

        {/* Action Button */}
        <div className="flex justify-center">
          <button className="px-8 py-4 bg-white text-black font-bold rounded-full hover:scale-105 transition-transform active:scale-95 shadow-xl shadow-white/10">
            Khám phá hệ thống
          </button>
        </div>
      </div>

      {/* Footer */}
      <footer className="mt-20 text-slate-600 text-sm">
        &copy; 2026 Tenacious SaaS Platform. Built with passion.
      </footer>
    </div>
  )
}

export default App
