import React from 'react';
import { Route, Routes } from 'react-router-dom';
import '@/App.css';
import Header from '@/components/Header';
import Footer from '@/components/Footer';
import Document from '@/components/Document';
import Sidebar from '@/components/Sidebar';
import BuilderPage from '@/components/BuilderPage';

function App() {
    return (
        <div className="App d-flex flex-column vh-100">
            <Header />
            <div className="row flex-grow-1 g-0" style={{ flexWrap: 'nowrap' }}>
                <div className="col-auto border-end bg-light">
                    <Sidebar />
                </div>
                <div className="col" style={{ overflowY: 'auto' }}>
                    <Routes>
                        <Route path="/document" element={<Document />} />
                        <Route path="/" element={<BuilderPage />} />
                    </Routes>
                </div>
            </div>
            {/* <Footer /> */}
        </div>
    );
}

export default App;