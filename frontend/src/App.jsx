// src/App.jsx (올바른 코드)
import React, { useState } from 'react';
import { Route, Routes } from 'react-router-dom';
import '@/App.css';
import Header from '@/components/Header';
import Sidebar from '@/components/Sidebar';
import BuilderCanvas from '@/components/BuilderCanvas';
import ModulesSet from '@/components/modulesSet';
import Footer from '@/components/Footer';
import Document from '@/components/Document';

import { DndContext, closestCenter, PointerSensor, useSensor, useSensors } from '@dnd-kit/core';
import useBuilderStore from '@/store';

const BuilderPage = () => {
    const { addItem, moveItem, items, updateItemStyle, selectItem, selectedItemId, deleteItem } = useBuilderStore();
    const [selectedModuleInfo, setSelectedModuleInfo] = useState(null);
    const sensors = useSensors(useSensor(PointerSensor, { activationConstraint: { distance: 2 } }));

    const handleDragEnd = (event) => {
        const { active, over } = event;
        if (active.data.current?.isSidebarModule && (over?.id === 'canvas' || over?.id === 'canvas-bottom-drop-zone')) {
            addItem({ type: active.data.current.type, renderedComponent: active.data.current.renderedComponent, properties: active.data.current.defaultProperties });
        } else if (over && active.id !== over.id && items.some(item => item.id === active.id) && items.some(item => item.id === over.id)) {
            const oldIndex = items.findIndex((item) => item.id === active.id);
            const newIndex = items.findIndex((item) => item.id === over.id);
            if (oldIndex !== -1 && newIndex !== -1) {
                moveItem(oldIndex, newIndex);
            }
        } else if (!over && items.some(item => item.id === active.id)) {
            deleteItem(active.id);
            selectItem(null);
            setSelectedModuleInfo(null);
        }
    };

    const handleStyleChange = (styles) => {
        if (selectedItemId) {
            updateItemStyle(selectedItemId, styles);
            setSelectedModuleInfo(items.find(item => item.id === selectedItemId));
        }
    };

    const handleClickItem = (e, id) => {
        e.stopPropagation();
        selectItem(id);
        setSelectedModuleInfo(items.find(item => item.id === id));
    };

    return (
        <DndContext sensors={sensors} collisionDetection={closestCenter} onDragEnd={handleDragEnd}>
            <div className="col-md-8 d-flex flex-column" style={{ overflowY: 'auto' }}>
                <BuilderCanvas onItemClick={handleClickItem} selectedItemId={selectedItemId} items={items} selectItem={selectItem} />
            </div>
            <div className="col-md-2 bg-light border-start p-3 d-flex flex-column" style={{ overflowY: 'auto' }}>
                <ModulesSet selectedModule={selectedModuleInfo} onStyleChange={handleStyleChange} />
            </div>
        </DndContext>
    );
};

function App() {
    return (
        <div className="App d-flex flex-column vh-100">
            <Header />
            <div className="row flex-grow-1 m-0 align-items-stretch" style={{ overflowY: 'auto' }}>
                <div className="col-md-2 bg-light border-end d-flex flex-column px-0" style={{ overflowY: 'auto', minHeight: 'calc(100vh - 80px)' }}>
                    <Sidebar />
                </div>
                <Routes>
                    <Route path="/document" element={<Document />} />
                    <Route path="/" element={<BuilderPage />} />
                </Routes>
            </div>
            <Footer />
        </div>
    );
}

export default App;