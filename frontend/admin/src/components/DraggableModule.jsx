import React from 'react';
import { useDraggable, DragOverlay } from '@dnd-kit/core';

function DraggableModule({ type, children, id, renderComponent }) {
    const { attributes, listeners, setNodeRef, isDragging } = useDraggable({
        id: id,
        data: {
            isSidebarModule: true,
            type: type,
            renderComponent: renderComponent,
        },
    });

    const style = {
        opacity: isDragging ? 0.5 : 1,
        cursor: 'grab',
    };

    return (
        <>
            <div ref={setNodeRef} style={style} {...listeners} {...attributes}>
                {children}
            </div>
            <DragOverlay dropAnimation={null}>
                {isDragging ? <div>{children}</div> : null}
            </DragOverlay>
        </>
    );
}

export default DraggableModule;