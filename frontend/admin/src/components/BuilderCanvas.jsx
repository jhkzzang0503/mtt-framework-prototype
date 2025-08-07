// src/components/BuilderCanvas.jsx

import React, { Suspense } from 'react';
import { useDroppable } from '@dnd-kit/core';
import { SortableContext, verticalListSortingStrategy } from '@dnd-kit/sortable';
import SortableItem from '@/components/SortableItem';
import useBuilderStore from '@/store'; // deleteItem을 위해 잠시 유지 또는 props로 받기

// BuilderPage로부터 필요한 props를 전달받습니다.
const BuilderCanvas = ({ items, selectedItemId, onItemClick, selectItem, deleteItem }) => {

    const { setNodeRef: setTopDropZoneRef, isOver: isOverTop } = useDroppable({
        id: 'top-drop-zone',
    });

    const topDropZoneStyle = {
        padding: '30px', // 약간 작게 조정
        border: isOverTop ? '2px solid #4A90E2' : '2px dashed #ccc',
        borderRadius: '8px',
        textAlign: 'center',
        color: isOverTop ? '#4A90E2' : '#999',
        backgroundColor: isOverTop ? '#e6f7ff' : 'transparent',
        transition: 'all 0.2s ease-in-out',
        marginBottom: '20px', // 아래 아이템들과의 간격
    };


    const renderComponent = (item) => {
        if (item.renderedComponent) {
            const LazyComponent = React.lazy(item.renderedComponent);
            return (
                <div style={item.styles?.customStyles}>
                    <Suspense fallback={<div>Loading...</div>}>
                        <LazyComponent />
                    </Suspense>
                </div>
            );
        }
        return <div>Error: Component not found for type: {item.type}</div>;
    };

    return (
        <div className="builder-canvas row align-items-start p-3"
             onClick={() => selectItem(null)}>
            <div className="w-100">
                <div ref={setTopDropZoneRef} style={topDropZoneStyle}>
                    <p>여기에 새 모듈을 드래그하여 추가하세요</p>
                </div>
            </div>
            <SortableContext items={items.map(item => item.id)} strategy={verticalListSortingStrategy}>
                {items.map(item => {
                    const userClasses = `${item.styles?.bootstrapClasses || ''} ${item.styles?.className || ''}`.trim();
                    return (
                        <SortableItem
                            key={item.id}
                            id={item.id}
                            onClick={(e) => onItemClick(e, item.id)}
                            isSelected={selectedItemId === item.id}
                            onDelete={deleteItem}
                            userClasses={userClasses}
                        >
                            {renderComponent(item)}
                        </SortableItem>
                    );
                })}
            </SortableContext>
        </div>
    );
};

export default BuilderCanvas;