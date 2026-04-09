#!/bin/bash

OUTPUT_FILE="result.txt"

echo "Сбор проекта начат..."

# Очистить файл если существует
> "$OUTPUT_FILE"

echo "================ TREE STRUCTURE ================" >> "$OUTPUT_FILE"

# Добавляем дерево проекта
tree -L 10 >> "$OUTPUT_FILE"

echo "" >> "$OUTPUT_FILE"
echo "================ FILE CONTENTS ================" >> "$OUTPUT_FILE"

# Перебор всех файлов
find . -type f \
    ! -path "./target/*" \
    ! -path "./.git/*" \
    ! -name "*.class" \
    ! -name "*.log" \
    ! -name "*.jar" \
    ! -name "result.txt" \
| while read file
do
    echo "" >> "$OUTPUT_FILE"
    echo "========== FILE: $file ==========" >> "$OUTPUT_FILE"
    cat "$file" >> "$OUTPUT_FILE"
done

echo ""
echo "Готово. Файл создан:"
echo "$OUTPUT_FILE"
