"use client";

import { useState } from "react";
import Formulario from "./Formulario";
import { Card, CardContent, CardFooter, CardHeader, CardTitle } from "./ui/card";

export interface Comodo {
  id: number;
  name: string;
}

const ComodoList = () => {
  const [comodos, setComodos] = useState<Comodo[]>([
    { id: 1, name: "Sala"},
    { id: 2, name: "Cozinha"},
    { id: 3, name : "Suite"},
    { id: 4, name : "Banheiro"} 
  ]);

  const [showForm, setShowForm] = useState(false);
  const [editingComodo, setEditingComodo] = useState<Comodo | null>(null);

  const handleSubmitComodo = (comodoData: Omit<Comodo, 'id'>) => {
    if (editingComodo) {
      setComodos(comodos.map((comodo) => (comodo.id === editingComodo.id ? { ...comodoData, id: editingComodo.id } : comodo)));
    } else {
      setComodos([...comodos, { id: Date.now(), ...comodoData }]);
    }
    setShowForm(false);
    setEditingComodo(null);
  };

  const handleEditComodo = (comodo: Comodo) => {
    setEditingComodo(comodo);
    setShowForm(true);
  };

  const handleDeleteComodo = (id: number) => {
    setComodos(comodos.filter((comodo) => comodo.id !== id));
  };

  return (
    <div className="p-6 flex-1 overflow-y-auto">
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-2xl font-semibold">Comodos do Imovel</h2>
        <button
          onClick={() => {
            setEditingComodo(null);
            setShowForm(true);
          }}
          className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
        >
          Adicionar Comodo
        </button>
      </div>

      <div className="overflow-x-auto">
        <table className="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
          <thead>
            <tr>
              <th className="py-3 px-6 bg-gray-200 text-left text-sm font-medium text-gray-700 uppercase tracking-wider">
                Nome
              </th>
              <th className="py-3 px-6 bg-gray-200 text-center text-sm font-medium text-gray-700 uppercase tracking-wider">
                Ações
              </th>
            </tr>
          </thead>
          <tbody>
            {comodos.map((comodo) => (
              <tr key={comodo.id} className="border-t">
                <td className="py-4 px-6 text-gray-800">{comodo.name}</td>
                <td className="py-4 px-6 text-center">
                  <button
                    onClick={() => handleEditComodo(comodo)}
                    className="bg-yellow-500 text-white px-3 py-1 mr-2 rounded hover:bg-yellow-600"
                  >
                    Editar
                  </button>
                  <button
                    onClick={() => handleDeleteComodo(comodo.id)}
                    className="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600"
                  >
                    Excluir
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Modal do Formulário */}
      {showForm && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
          <Card className="w-full max-w-md">
            <CardHeader className="bg-[#FFC314]">
              <CardTitle>{editingComodo ? "Editar comodo" : "Adicionar novo comodo"}</CardTitle>
            </CardHeader>
            <CardContent>
              <Formulario onSubmit={handleSubmitComodo} setState={setShowForm} />
            </CardContent>
            <CardFooter>
              <button
                onClick={() => {
                  setShowForm(false);
                  setEditingComodo(null);
                }}
                className="w-full bg-gray-500 text-white py-2 rounded hover:bg-gray-600"
              >
                Cancelar
              </button>
            </CardFooter>
          </Card>
        </div>
      )}
    </div>
  );
};

export default ComodoList;
