// src/components/BuilderCanvas.jsx (수정)
import React, { Suspense } from 'react';
import {SortableContext, verticalListSortingStrategy} from '@dnd-kit/sortable';
import {useDroppable} from '@dnd-kit/core';
import useBuilderStore from '@/store';
import SortableItem from '@/components/SortableItem';
import ModulesLayout from '@/components/ModulesLayout';

const BuilderCanvas = ({onItemClick, selectedItemId, items, selectItem}) => {
    const {deleteItem} = useBuilderStore();

    // 캔버스 전체에 대한 useDroppable 훅을 제거
    // const {setNodeRef: setCanvasRef, isOver: isOverCanvas} = useDroppable({ id: 'canvas' });

    const { setNodeRef: setBottomDropZoneRef, isOver: isOverBottom } = useDroppable({
        id: 'canvas-bottom-drop-zone',
    });

    const bottomDropZoneStyle = {
        marginTop: '20px',
        marginBottom: '20px',
        padding: '40px',
        border: isOverBottom ? '2px solid #4A90E2' : '2px dashed #ccc',
        borderRadius: '8px',
        textAlign: 'center',
        color: isOverBottom ? '#4A90E2' : '#999',
        backgroundColor: isOverBottom ? '#e6f7ff' : 'transparent',
        transition: 'all 0.2s ease-in-out',
    };

    const canvasStyle = {
        // isOver 관련 로직 제거
        transition: 'background-color 0.2s ease-in-out, border 0.2s ease-in-out',
        flexGrow: 1,
        position: 'relative',
        overflowX: 'hidden',
    };

    const renderComponent = (item) => {
        if (item.renderedComponent) {
            const LazyComponent = React.lazy(item.renderedComponent);
            try {
                return (
                    <div style={item.styles?.customStyles}>
                        <Suspense fallback={<div>Loading...</div>}>
                            <LazyComponent />
                        </Suspense>
                    </div>
                );
            } catch (error) {
                console.error("Error rendering component:", error);
                return <div>Error: Failed to apply styles to component.</div>;
            }
        }
        return <div>Error: Component not found for type: {item.type}</div>;
    };

    const localHandleClick = (e, id) => {
        if (onItemClick) {
            onItemClick(e, id);
        }
    };

    return (
        <div className="row h-100">
            <div className="col-md-3 px-0">
                <ModulesLayout/>
            </div>
            <div className="col-md-9">
                <div
                    // 캔버스 전체에 대한 ref를 제거
                    className="builder-canvas row align-items-start"
                    onClick={(e) => {
                        localHandleClick(e, null);
                    }}
                    style={canvasStyle}
                >
                    <SortableContext items={items.map(item => item.id)} strategy={verticalListSortingStrategy}>
                        {items.map(item => {
                            const userClasses = `${item.styles?.bootstrapClasses || ''} ${item.styles?.className || ''}`.trim();
                            return (
                                <SortableItem
                                    key={item.id}
                                    id={item.id}
                                    onClick={(e) => localHandleClick(e, item.id)}
                                    isSelected={selectedItemId === item.id}
                                    onDelete={deleteItem}
                                    userClasses={userClasses}
                                >
                                    {renderComponent(item)}
                                </SortableItem>
                            )
                        })}
                    </SortableContext>
                    <div className="canvas-placeholder" ref={setBottomDropZoneRef} style={bottomDropZoneStyle}>
                        <p>여기에 모듈을 드래그하여 추가하세요</p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default BuilderCanvas;